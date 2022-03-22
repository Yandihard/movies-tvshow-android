package com.yandihard.tvmoviesapp.submission.datadummies

import com.yandihard.tvmoviesapp.submission.source.local.DetailMovieEntity
import com.yandihard.tvmoviesapp.submission.source.local.GenreEntity
import com.yandihard.tvmoviesapp.submission.source.local.LanguageEntity
import com.yandihard.tvmoviesapp.submission.source.local.MovieEntity
import com.yandihard.tvmoviesapp.submission.source.remote.response.DetailMovie
import com.yandihard.tvmoviesapp.submission.source.remote.response.GenresItem
import com.yandihard.tvmoviesapp.submission.source.remote.response.ResultsMovies
import com.yandihard.tvmoviesapp.submission.source.remote.response.SpokenLanguagesItem

object MoviesData {
    fun generateDummyMovies() : ArrayList<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(MovieEntity("In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "Godzilla vs. Kong",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/dA6mPgdFMA03MjerpnndYytyKdT.jpg",
                101,))
        movies.add(MovieEntity("Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                "Zack Snyder's Justice League",
        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
        102))
        movies.add(MovieEntity("When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "Space Sweepers",
        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xRWht48C2V8XNfzvPehyClOvDni.jpg",
        103))
        movies.add(MovieEntity("Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "Raya and the Last Dragon",
        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/uCg2HPY7rBCrh1YGpXam9LH1xKZ.jpg",
        104))
        movies.add(MovieEntity("Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "Sentinelle",
        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg",
        105))
        movies.add(MovieEntity("The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "Tom & Jerry",
        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hXgmWPd1SuujRZ4QnKLzrj79PAw.jpg",
        106))
        movies.add(MovieEntity("Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "Monster Hunter",
        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qPQFWrLoQYyGxmeBgmpX901Q0mF.jpg",
        107))
        movies.add(MovieEntity("Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                "Wonder Woman 1984",
        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/iiZZdoQBEYBv6id8su7ImL0oCbD.jpg",
        108))
        movies.add(MovieEntity("France, June 1944. On the eve of D-Day, some American paratroopers fall behind enemy lines after their aircraft crashes while on a mission to destroy a radio tower in a small village near the beaches of Normandy. After reaching their target, the surviving paratroopers realise that, in addition to fighting the Nazi troops that patrol the village, they also must fight against something else.",
                "Bajocero",
        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2Sfo3O54kTAAnBfZaCXrwimORSo.jpg",
        109))
        movies.add(MovieEntity("As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "Cherry",
        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg",
        110))
        return movies
    }

    fun generateRemoteDummyMovies() : ArrayList<ResultsMovies> {
        val movies = ArrayList<ResultsMovies>()

        movies.add(ResultsMovies("In 1944, a courageous group of Russian soldiers managed to escape from German captivity in a half-destroyed legendary T-34 tank. Those were the times of unforgettable bravery, fierce fighting, unbreakable love, and legendary miracles.",
                "T-34 (2018)",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/dA6mPgdFMA03MjerpnndYytyKdT.jpg",
                101,))
        movies.add(ResultsMovies("Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                "A Star Is Born (2018)",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
                102))
        movies.add(ResultsMovies("When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "Alita: Battle Angel (2019)",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xRWht48C2V8XNfzvPehyClOvDni.jpg",
                103))
        movies.add(ResultsMovies("Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "Aquaman (2018)",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/uCg2HPY7rBCrh1YGpXam9LH1xKZ.jpg",
                104))
        movies.add(ResultsMovies("Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "Bohemian Rhapsody (2018)",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg",
                105))
        movies.add(ResultsMovies("The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "Cold Pursuit (2019)",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hXgmWPd1SuujRZ4QnKLzrj79PAw.jpg",
                106))
        movies.add(ResultsMovies("Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "Creed II (2018)",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qPQFWrLoQYyGxmeBgmpX901Q0mF.jpg",
                107))
        movies.add(ResultsMovies("Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                "Spider-Man: Into the Spider-Verse (2018)",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/iiZZdoQBEYBv6id8su7ImL0oCbD.jpg",
                108))
        movies.add(ResultsMovies("France, June 1944. On the eve of D-Day, some American paratroopers fall behind enemy lines after their aircraft crashes while on a mission to destroy a radio tower in a small village near the beaches of Normandy. After reaching their target, the surviving paratroopers realise that, in addition to fighting the Nazi troops that patrol the village, they also must fight against something else.",
                "Overlord (2018)",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2Sfo3O54kTAAnBfZaCXrwimORSo.jpg",
                109))
        movies.add(ResultsMovies("As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "Avengers: Infinity War (2018)",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg",
                110))
        return movies
    }

    private val moviesName = arrayOf(
            "Zack Snyder's Justice League",
            "Godzilla vs. Kong",
            "Space Sweepers",
            "Raya and the Last Dragon",
            "Sentinelle",
            "Tom & Jerry",
            "Monster Hunter",
            "Wonder Woman 1984",
            "Bajocero",
            "Cherry")

    private val moviesAired = arrayOf("01/01/2019",
            "10/05/2018",
            "02/14/2019",
            "12/21/2018",
            "11/02/2018",
            "02/08/2019",
            "11/21/2018",
            "12/14/2018",
            "11/09/2018",
            "04/27/2018")

    private val moviesGenre = arrayOf("War, Action, Drama, History",
            "Drama, Romance, Music",
            "Action, Science Fiction, Adventure",
            "Action, Adventure, Fantasy",
            "Music, Drama",
            "Action, Crime, Thriller",
            "Drama",
            "Action, Adventure, Science Fiction, Comedy",
            "Horror, War, Science Fiction",
            "Adventure, Action, Science Fiction")

    private val moviesStatus = arrayOf("Released", "Released", "Released", "Released", "Released", "Released", "Released", "Released", "Released", "Released")

    private val moviesRating = doubleArrayOf(6.8,
        7.5,
        7.1,
        6.9,
        8.0,
        5.6,
        6.9,
        8.4,
        6.7,
        8.3)
    private val moviesLang = arrayOf("Russian", "English", "English", "English", "English", "English", "English", "English", "English", "English")

    private val moviesBudget = intArrayOf(8000000,
            36000000,
            170000000,
            160000000,
            52000000,
            60000000,
            50000000,
            90000000,
            38000000,
            300000000)

    private val moviesRevenue = intArrayOf(36220872,
            433888866,
            404852543,
            1148461807,
            894227543,
            59213931,
            214215889,
            375540831,
            41657844,
            224623967
    )

    private val moviesOverview = arrayOf("Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
            "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
            "When the crew of a space junk collector ship called The Victory discovers a humanoid robot named Dorothy that's known to be a weapon of mass destruction, they get involved in a risky business deal which puts their lives at stake.",
            "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",
            "Transferred home after a traumatizing combat mission, a highly trained French soldier uses her lethal skills to hunt down the man who hurt her sister.",
            "Tom the cat and Jerry the mouse get kicked out of their home and relocate to a fancy New York hotel, where a scrappy employee named Kayla will lose her job if she can’t evict Jerry before a high-class wedding at the hotel. Her solution? Hiring Tom to get rid of the pesky mouse.",
            "A portal transports Cpt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.",
            "A botched store robbery places Wonder Woman in a global battle against a powerful and mysterious ancient force that puts her powers in jeopardy.",
            "When a prisoner transfer van is attacked, the cop in charge must fight those inside and outside while dealing with a silent foe: the icy temperatures.",
            "Cherry drifts from college dropout to army medic in Iraq - anchored only by his true love, Emily. But after returning from the war with PTSD, his life spirals into drugs and crime as he struggles to find his place in the world.",
            "In the near future, a drone pilot is sent into a deadly militarized zone and must work with an android officer to locate a doomsday device.")

    private val moviesId = intArrayOf(101,
            102,
            103,
            104,
            105,
            106,
            107,
            108,
            109,
            110,
            111)

    private val moviesPoster = arrayOf("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/dA6mPgdFMA03MjerpnndYytyKdT.jpg",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xRWht48C2V8XNfzvPehyClOvDni.jpg",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/uCg2HPY7rBCrh1YGpXam9LH1xKZ.jpg",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hXgmWPd1SuujRZ4QnKLzrj79PAw.jpg",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qPQFWrLoQYyGxmeBgmpX901Q0mF.jpg",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/iiZZdoQBEYBv6id8su7ImL0oCbD.jpg",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2Sfo3O54kTAAnBfZaCXrwimORSo.jpg",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6XYLiMxHAaCsoyrVo38LBWMw2p8.jpg")

    fun generateDummyMovie() : DetailMovieEntity {
        lateinit var movie: DetailMovieEntity
        for (position in moviesName.indices) {
            movie = DetailMovieEntity(name = moviesName[position],
                    revenue = moviesRevenue[position],
                    movieId = moviesId[position],
                    budget = moviesBudget[position],
                    overview = moviesOverview[position],
                    image = moviesPoster[position],
                    release = moviesAired[position],
                    score = moviesRating[position],
                    status = moviesStatus[position])
        }
        return movie
    }

    fun generateRemoteDummyMovie() : DetailMovie {
        lateinit var movie: DetailMovie
        for (position in moviesName.indices) {
            movie = DetailMovie(title = moviesName[position],
                    revenue = moviesRevenue[position],
                    id = moviesId[position],
                    budget = moviesBudget[position],
                    overview = moviesOverview[position],
                    posterPath = moviesPoster[position],
                    releaseDate = moviesAired[position],
                    voteAverage = moviesRating[position],
                    status = moviesStatus[position])
        }
        return movie
    }

    fun generateDummyGenreM() : List<GenreEntity> {
        val genreResult = ArrayList<GenreEntity>()
        for (position in moviesName.indices) {
            val movie = GenreEntity()
            movie.name = moviesGenre[position]
            genreResult.add(movie)
        }
        return genreResult
    }

    fun generateRemoteDummyGenreM() : List<GenresItem> {
        val genreResult = ArrayList<GenresItem>()
        for (position in moviesName.indices) {
            val movie = GenresItem()
            movie.name = moviesGenre[position]
            genreResult.add(movie)
        }
        return genreResult
    }

    fun generateDummyLangM() : List<LanguageEntity> {
        val langResult = ArrayList<LanguageEntity>()
        for (position in moviesName.indices) {
            val movie = LanguageEntity()
            movie.name = moviesLang[position]
            langResult.add(movie)
        }
        return langResult
    }

    fun generateRemoteDummyLangM() : List<SpokenLanguagesItem> {
        val langResult = ArrayList<SpokenLanguagesItem>()
        for (position in moviesName.indices) {
            val movie = SpokenLanguagesItem()
            movie.name = moviesLang[position]
            langResult.add(movie)
        }
        return langResult
    }
}