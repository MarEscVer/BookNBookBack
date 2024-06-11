package org.reader.low.booknbook.controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.controller.interfaz.DenunciaController;
import org.reader.low.booknbook.controller.request.denuncia.DenunciaRequest;
import org.reader.low.booknbook.controller.response.IdResponse;
import org.reader.low.booknbook.service.DenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Denuncia controller.
 */
@Slf4j
@NoArgsConstructor
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/denuncia")
public class DenunciaControllerImpl implements DenunciaController {

    /**
     * The Denuncia service.
     */
    @Autowired
    private DenunciaService denunciaService;

    @Override
    public IdResponse hacerDenuncia(DenunciaRequest request) {
        return denunciaService.hacerDenuncia(request);
    }
}
