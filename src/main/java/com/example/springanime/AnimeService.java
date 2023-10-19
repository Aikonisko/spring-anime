package com.example.springanime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimeService {

    private final AnimeRepository animeRepository;

    @Autowired
    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public List<Anime> getAllAnime() {
        return animeRepository.findAll();
    }

    public Anime getAnimeById(Long id) {
        Optional<Anime> optionalAnime = animeRepository.findById(id);
        return optionalAnime.orElse(null);
    }

    public Anime createAnime(Anime anime) {
        return animeRepository.save(anime);
    }

    public Anime updateAnime(Long id, Anime updatedAnime) {
        Optional<Anime> existingAnimeOptional = animeRepository.findById(id);
        if (existingAnimeOptional.isPresent()) {
            Anime existingAnime = existingAnimeOptional.get();
            existingAnime.setTitle(updatedAnime.getTitle());
            existingAnime.setGenre(updatedAnime.getGenre());
            return animeRepository.save(existingAnime);
        } else {
            return null; // Handle error, anime with given ID not found
        }
    }

    public void deleteAnime(Long id) {
        animeRepository.deleteById(id);
    }
}
