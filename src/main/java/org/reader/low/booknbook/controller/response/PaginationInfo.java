package org.reader.low.booknbook.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaginationInfo {

    private Integer elementsPerPage;

    private Integer totalPages;

    private Integer firstPage;

    private Integer lastPage;

    private Integer totalElements;

    private Integer actualPage;
}
