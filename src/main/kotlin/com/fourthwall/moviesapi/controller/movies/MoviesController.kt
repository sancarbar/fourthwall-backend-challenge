package com.fourthwall.moviesapi.controller.movies

import com.fourthwall.moviesapi.controller.movies.dto.MovieInfoDto
import com.fourthwall.moviesapi.controller.movies.dto.MovieTimesDto
import com.fourthwall.moviesapi.controller.movies.dto.ReviewDto
import com.fourthwall.moviesapi.data.movie.MovieShowTimes
import com.fourthwall.moviesapi.data.user.ROLE_OWNER
import com.fourthwall.moviesapi.service.movies.MoviesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import javax.annotation.security.RolesAllowed

@RestController
@RequestMapping("/v1/movies")
class MoviesController(@Autowired val moviesService: MoviesService) {

    @Value("\${app.omdb_api_key}")
    private val omdbApiKey: String? = null


    @GetMapping("/{imdbId}")
    fun getMovieDetails(@PathVariable imdbId: String): MovieInfoDto? {
        val restTemplate = RestTemplate()
        val uri = "http://www.omdbapi.com/?apikey=$omdbApiKey&i=$imdbId"
        return restTemplate.getForObject(uri, MovieInfoDto::class.java)
    }

    @GetMapping("/{imdbId}/times")
    fun getMovieTimes(@PathVariable imdbId: String): MovieShowTimes? {
        return moviesService.getMovieShowTimes(imdbId)
    }

    @PostMapping("/{imdbId}/review")
    fun reviewMovie(@PathVariable imdbId: String, @RequestBody reviewDto: ReviewDto): MovieShowTimes? {
        return moviesService.review(imdbId, reviewDto.review)
    }

    @PostMapping("/{imdbId}")
    @RolesAllowed(ROLE_OWNER)
    fun updateMovieTimes(@PathVariable imdbId: String, @RequestBody movieTimesDto: MovieTimesDto): MovieShowTimes? {
        return moviesService.updateMovieShowTimes(movieTimesDto.name, imdbId, movieTimesDto.movieShows)
    }


}