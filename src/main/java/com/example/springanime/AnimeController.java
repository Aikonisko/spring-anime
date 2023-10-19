package com.example.springanime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AnimeController {

    private final AnimeService animeService;

    @Autowired
    public AnimeController(AnimeService animeService) {
        this.animeService = animeService;
    }

    @GetMapping("/anime")
    public String getAllAnime(Model model) {
        List<Anime> allAnime = animeService.getAllAnime();
        model.addAttribute("allAnime", allAnime);
        return "anime";
    }

    @GetMapping("/anime/{id}")
    public String getAnimeById(@PathVariable Long id, Model model) {
        Anime animeById = animeService.getAnimeById(id);
        model.addAttribute("animeById", animeById);
        return "anime";
    }

    @PostMapping("/anime")
    public String createAnime(@ModelAttribute Anime anime) {
        animeService.createAnime(anime);
        return "redirect:/anime";
    }

    @PostMapping("/anime/{id}/update")
    public String updateAnime(@PathVariable Long id, @ModelAttribute Anime updatedAnime) {
        animeService.updateAnime(id, updatedAnime);
        return "redirect:/anime";
    }

    @PostMapping("/anime/{id}/delete")
    public String deleteAnime(@PathVariable Long id) {
        animeService.deleteAnime(id);
        return "redirect:/anime";
    }
}
