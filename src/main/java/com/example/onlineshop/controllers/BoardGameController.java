package com.example.onlineshop.controllers;


import com.example.onlineshop.entity.product.Products;
import com.example.onlineshop.entity.product.boardGame.BoardGame;
import com.example.onlineshop.repository.BoardGameRepository;
import com.example.onlineshop.repository.ProductsRepository;
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
    private final ProductsRepository productsRepository;
    @Autowired
    public BoardGameController(BoardGameRepository boardGameRepository, ProductsRepository productsRepository) {
        this.boardGameRepository = boardGameRepository;
    this.productsRepository = productsRepository;
}
    @GetMapping("/bg")
    public String findAll(Model model) {
        List<BoardGame> boardGames = boardGameRepository.findAll();
        List<Products> products=productsRepository.findAll();
               model.addAttribute("boardGames", boardGames);
               model.addAttribute("products",products);
        return "board-games";
    }

    @GetMapping("/bg-create")
    public String createBoardGameForm(BoardGame boardGame) {
        return "bg-create";
    }

    @PostMapping("/bg-create")
    public String createBoardGame(BoardGame boardGame) {
        boardGameRepository.save(boardGame);
        return "redirect:/bg";
    }

    @GetMapping("/bg-delete/{productId}")
    public String deleteBoardGame(@PathVariable("productId") Long productId) {
        boardGameRepository.deleteById(productId);
        return "redirect:/bg";
    }

    @GetMapping("/bg-update/{productId}")
    public String updateBoardGameForm(@PathVariable("productId") Long productId, Model model) {
        BoardGame boardGame = boardGameRepository.getReferenceById(productId);
        model.addAttribute("boardGame", boardGame);
        return "bg-update";
    }

    @PostMapping("/bg-update")
    public String updateBoardGame(BoardGame boardGame) {
        boardGameRepository.save(boardGame);
        return "redirect:/bg";
    }

}
