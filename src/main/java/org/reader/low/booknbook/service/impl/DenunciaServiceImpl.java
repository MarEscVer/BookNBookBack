package org.reader.low.booknbook.service.impl;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.config.error.hander.BadRequestHanderException;
import org.reader.low.booknbook.controller.object.ModerateComments;
import org.reader.low.booknbook.controller.request.denuncia.DenunciaRequest;
import org.reader.low.booknbook.controller.response.IdResponse;
import org.reader.low.booknbook.mapper.ResponseMapping;
import org.reader.low.booknbook.model.bnb.Denuncia;
import org.reader.low.booknbook.model.bnb.Valoracion;
import org.reader.low.booknbook.model.bnb.id.IdValoracion;
import org.reader.low.booknbook.persistence.repository.DenunciaRepository;
import org.reader.low.booknbook.persistence.repository.ValoracionRepository;
import org.reader.low.booknbook.service.DenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.reader.low.booknbook.mapper.FiltersMapping.filtroModerateComments;
import static org.reader.low.booknbook.utils.ApplicationUtils.filteringListPage;

/**
 * The type Denuncia service.
 */
@Slf4j
@NoArgsConstructor
@Service
public class DenunciaServiceImpl implements DenunciaService {

    /**
     * The Denuncia repository.
     */
    @Autowired
    private DenunciaRepository denunciaRepository;

    /**
     * The Valoracion repository.
     */
    @Autowired
    private ValoracionRepository valoracionRepository;

    @Override
    public void estadoDenuncia(Long idDenuncia, String estado) {
        Optional<Denuncia> denunciaOptional = denunciaRepository.findById(idDenuncia);
        if(denunciaOptional.isPresent()){
            Denuncia denuncia = denunciaOptional.get();
            denuncia.setEstado(estado);
            denunciaRepository.save(denuncia);
        }else {
            throw new BadRequestHanderException("denuncia_existe","La denuncia no existe");
        }
    }

    @Override
    public IdResponse hacerDenuncia(DenunciaRequest request) {
        Denuncia denuncia = Denuncia.builder()
                .fecha(new Date(Instant.now().toEpochMilli()))
                .texto(request.getTexto())
                .motivo(request.getMotivo())
                .estado(request.isGrupo() ? "GRUPO_PENDIENTE" : "PENDIENTE")
                .build();
        Optional<Valoracion> valoracion = valoracionRepository.findById(IdValoracion.builder()
                .idLibro(request.getIdLibro()).idUsuario(request.getIdUsuario()).build());
        if(valoracion.isPresent()){
            Valoracion valoracionGet = valoracion.get();
            valoracionGet.setDenuncia(denuncia);
            valoracionGet = valoracionRepository.save(valoracionGet);
            return IdResponse.builder()
                    .id(valoracionGet.getDenuncia().getId()).message("Valoracion denunciada correctamente").build();
        }
        throw new BadRequestHanderException("denuncia_crear","La denuncia no se ha podido llevar a cabo");
    }

    @Override
    public List<ModerateComments> listaComentariosDenunciados(Integer pageIndex, Integer size, String filter, String estado){
        List<Valoracion> valoracionList = valoracionRepository.findByDenunciaNotNullOrderByFechaComentarioDesc();
        Pageable pageable = PageRequest.of(pageIndex, size);

        List<ModerateComments> denuncias = valoracionList.stream()
                .filter(valoracion -> estado.equals(valoracion.getDenuncia().getEstado()))
                .map(ResponseMapping::maptToModerateComments)
                .filter(comentario -> filtroModerateComments(filter, comentario)).toList();
        Page<ModerateComments> moderateComments = new PageImpl<>(
                filteringListPage(denuncias, pageable),
                pageable, denuncias.size());
        return moderateComments.toList();
    }
}
