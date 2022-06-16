package com.example.onlineshop.controllers;

import com.example.onlineshop.entity.order.Order;
import com.example.onlineshop.entity.product.ProductType;
import com.example.onlineshop.entity.product.SEO;
import com.example.onlineshop.entity.product.Sale;
import com.example.onlineshop.entity.product.boardGame.*;
import com.example.onlineshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoardGameController {
    private final BoardGameRepository boardGameRepository;
    private final BoardGameProducerRepository boardGameProducerRepository;
    private final DifficultyLevelRepository difficultyLevelRepository;
    private final GenreRepository genreRepository;
    private final AgeRepository ageRepository;
    private final PlayTimeRepository playTimeRepository;
    private final OptionRepository optionRepository;
    private final SEORepository seoRepository;
    private final ProductTypeRepository productTypeRepository;
    private final SaleRepository saleRepository;

    @Autowired
    public BoardGameController(BoardGameRepository boardGameRepository, BoardGameProducerRepository boardGameProducerRepository, DifficultyLevelRepository difficultyLevelRepository, OptionRepository optionRepository, PlayTimeRepository playTimeRepository, GenreRepository genreRepository, AgeRepository ageRepository, SEORepository seoRepository, ProductTypeRepository productTypeRepository, SaleRepository saleRepository) {
        this.boardGameRepository = boardGameRepository;
        this.boardGameProducerRepository = boardGameProducerRepository;
        this.difficultyLevelRepository = difficultyLevelRepository;
        this.optionRepository = optionRepository;
        this.playTimeRepository = playTimeRepository;
        this.genreRepository = genreRepository;
        this.ageRepository = ageRepository;
        this.seoRepository = seoRepository;
        this.productTypeRepository = productTypeRepository;
        this.saleRepository = saleRepository;
    }

    @GetMapping("/games")
    public String findAll(Model model) {
        List<BoardGame> boardGames = boardGameRepository.findAll();
        model.addAttribute("boardGames", boardGames);
        return "show-games";
    }

    @GetMapping("/games-create")
    public String createBoardGameForm(Model model) {
        model.addAttribute("boardGameProducers", boardGameProducerRepository.findAll());
        model.addAttribute("difficultyLevels", difficultyLevelRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("ages", ageRepository.findAll());
        model.addAttribute("options", optionRepository.findAll());
        model.addAttribute("playTimes", playTimeRepository.findAll());
        model.addAttribute("seos", seoRepository.findAll());
        model.addAttribute("productTypes", productTypeRepository.findAll());
        model.addAttribute("sales", saleRepository.findAll());
        model.addAttribute("boardGame", new BoardGame());
        return "games-create";
    }

    @PostMapping("/games-create")
    public String createBoardGame(BoardGame boardGame) {
        boardGameRepository.save(boardGame);

        return "redirect:/games";
    }

    @GetMapping("/games-delete/{id}")
    public String deleteBoardGame(@PathVariable("id") Long id) {
        boardGameRepository.deleteById(id);
        return "redirect:/games";
    }

    @GetMapping("/games-update/{id}")
    public String updateBoardGameForm(@PathVariable("id") Long id, Model model) {
        BoardGame boardGame = boardGameRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid type ID" + id));
        model.addAttribute("boardGame", boardGame);

        model.addAttribute("boardGameProducers", boardGameProducerRepository.findAll());
        model.addAttribute("difficultyLevels", difficultyLevelRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("ages", ageRepository.findAll());
        model.addAttribute("options", optionRepository.findAll());
        model.addAttribute("playTimes", playTimeRepository.findAll());
        model.addAttribute("seos", seoRepository.findAll());
        model.addAttribute("productTypes", productTypeRepository.findAll());
        model.addAttribute("sales", saleRepository.findAll());

        return "games-update";
    }

    @PostMapping("/games-update")
    public String updateBoardGame(BoardGame boardGame) {
        boardGameRepository.save(boardGame);
        return "redirect:/games";
    }

    //метод для добавления в модель  вместо повторения всех строк
}
