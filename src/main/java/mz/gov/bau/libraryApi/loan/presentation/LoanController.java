package mz.gov.bau.libraryApi.loan.presentation;

import lombok.RequiredArgsConstructor;
import mz.gov.bau.libraryApi.loan.domain.LoanQuery;
import mz.gov.bau.libraryApi.loan.domain.mapper.LoanMapper;
import mz.gov.bau.libraryApi.loan.service.LoanService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/loans")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService service;

    @GetMapping
    public ResponseEntity<Page<LoanJson>> findAll(LoanQuery query, @PageableDefault Pageable pageable,
                                                  @RequestParam(required = false, defaultValue = "false",
                                                          name = "unpaged") Boolean unpaged) {
        return ResponseEntity.ok(LoanMapper.INSTANCE.toJson(service.findAll(query,pageable,unpaged)));
    }

}