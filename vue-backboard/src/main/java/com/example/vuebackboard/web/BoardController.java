package com.example.vuebackboard.web;

import com.example.vuebackboard.dto.BoardDto;
import com.example.vuebackboard.entity.BoardEntity;
import com.example.vuebackboard.services.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/api/board/list")
    public List<BoardDto> boardList(){
        return boardService.getBoardList();
    }

    @GetMapping("/api/board/{idx}")
    public BoardDto getBoard(@PathVariable("idx") Long idx ){
        return boardService.getBoard(idx);
    }

    @PostMapping("/api/board")
    public BoardEntity create(@RequestBody BoardDto boardDto){
        return boardService.create(boardDto);
    }

    @PatchMapping("/api/board")
    public BoardEntity update(@RequestBody BoardDto boardDto){
        return boardService.update(boardDto);
    }

    @DeleteMapping("/api/board/{id}")
    public void delete(@PathVariable Long id){
        boardService.delete(id);
    }
}
