package com.example.javainterview.cotroller;

import com.example.javainterview.dto.ResponseDto;
import com.example.javainterview.dto.TopResponseDto;
import com.example.javainterview.service.URLService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class URLShortenerController {

    private URLService service;

    public URLShortenerController(final URLService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<ResponseDto> urlShortener(@RequestParam String url) {
        String shortVersion = service.urlShortener(url);
        ResponseDto dto = new ResponseDto(url, shortVersion);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/top-100")
    public ResponseEntity<List<TopResponseDto>> top100() {
        return ResponseEntity.ok(service.getTop100().stream().map(TopResponseDto::new).toList());
    }
}
