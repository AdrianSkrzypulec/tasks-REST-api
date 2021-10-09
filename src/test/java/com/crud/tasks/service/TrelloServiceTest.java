package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private SimpleEmailService simpleEmailService;


    @Test
    public void testFetchTrelloBoards() {

        // Given
        List<TrelloListDto> trelloList = new ArrayList<>();
        trelloList.add(new TrelloListDto("1", "Name", false));

        List<TrelloBoardDto> trelloBoard = new ArrayList<>();
        trelloBoard.add(new TrelloBoardDto("2", "Name", trelloList));

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoard);

        // When
        List<TrelloBoardDto> trelloBoardDtoList = trelloService.fetchTrelloBoards();

        // Then
        assertEquals("2", trelloBoardDtoList.get(0).getId());
        assertEquals("Name", trelloBoardDtoList.get(0).getLists().get(0).getName());
    }

    @Test
    public void testCreateTrelloCard() {

        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "name", "desc", "pos", "1");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto(
                "id",
                "name",
                "url",
                new Badges()
        );

        when(trelloService.createTrelloCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        // When
        CreatedTrelloCardDto resultCreatedTrelloCardDto = trelloService.createTrelloCard(trelloCardDto);

        // Then
        assertEquals("id", resultCreatedTrelloCardDto.getId());
        assertEquals("name", resultCreatedTrelloCardDto.getName());
        assertEquals("url", resultCreatedTrelloCardDto.getShortUrl());

        assertEquals(createdTrelloCardDto.getId(), resultCreatedTrelloCardDto.getId());
        assertEquals(createdTrelloCardDto.getName(), resultCreatedTrelloCardDto.getName());
        assertEquals(createdTrelloCardDto.getShortUrl(), resultCreatedTrelloCardDto.getShortUrl());
        assertEquals(createdTrelloCardDto.getBadges(), resultCreatedTrelloCardDto.getBadges());
    }
}