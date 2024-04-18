package com.example.vuebackboard.services;

import com.example.vuebackboard.Repository.BoardRepository;
import com.example.vuebackboard.dto.BoardDto;
import com.example.vuebackboard.entity.BoardEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardDto> getBoardList(){
        List<BoardEntity> boardEntities = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for(BoardEntity boardEntity : boardEntities){
            BoardDto dto = BoardDto.builder()
                    .idx(boardEntity.getIdx())
                    .author(boardEntity.getAuthor())
                    .title(boardEntity.getTitle())
                    .contents(boardEntity.getContents())
                    .createdAt(boardEntity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .build();

            boardDtoList.add(dto);
        }
         return boardDtoList;
    }

    public BoardDto getBoard(Long id){
        BoardEntity entity = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        return BoardDto.builder()
                .idx(entity.getIdx())
                .title(entity.getTitle())
                .contents(entity.getContents())
                .author(entity.getAuthor())
                .createdAt(entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                .build();
    }

    public BoardEntity create(BoardDto boardDto){
        return boardRepository.save(BoardEntity.builder()
                .author(boardDto.getAuthor())
                .title(boardDto.getTitle())
                .contents(boardDto.getContents())
                .build());
    }

    public BoardEntity update(BoardDto boardDto){
        BoardEntity entity = boardRepository.findById(boardDto.getIdx()).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        entity.setTitle(boardDto.getTitle());
        entity.setContents(boardDto.getContents());
        return boardRepository.save(entity);
    }

    public void delete(Long id){
        BoardEntity entity = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        boardRepository.delete(entity);
    }
}
