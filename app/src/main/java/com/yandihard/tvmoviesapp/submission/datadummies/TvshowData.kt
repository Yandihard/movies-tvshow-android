package com.yandihard.tvmoviesapp.submission.datadummies

import com.yandihard.tvmoviesapp.submission.source.local.*
import com.yandihard.tvmoviesapp.submission.source.remote.response.*

object TvshowData {

    fun generateDummyTvshows() : ArrayList<TvEntity> {
        val list = arrayListOf<TvEntity>()
        for (position in tvshowsName.indices) {
            val tvshow = TvEntity()
            tvshow.overview = tvshowsOverview[position]
            tvshow.image = tvshowsPoster[position]
            tvshow.name = tvshowsName[position]
            tvshow.tvId = tvshowsId[position]
            list.add(tvshow)
        }
        return list
    }

    fun generateRemoteDummyTvshows() : ArrayList<ResultsTv> {
        val list = arrayListOf<ResultsTv>()
        for (position in tvshowsName.indices) {
            val tvshow = ResultsTv()
            tvshow.overview = tvshowsOverview[position]
            tvshow.posterPath = tvshowsPoster[position]
            tvshow.name = tvshowsName[position]
            tvshow.id = tvshowsId[position]
            list.add(tvshow)
        }
        return list
    }

    private val tvshowsName = arrayOf("Game of Thrones",
            "The Flash",
            "Riverdale",
            "WandaVision",
            "Grey's Anatomy",
            "Superman & Lois",
            "The Good Doctor",
            "The Walking Dead",
            "Lucifer",
            "Sky Rojo")

    private val tvshowsPoster = arrayOf("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qOxedwaJzdms2alAmIEHEnDeDzg.jpg",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qcr9bBY6MVeLzriKCmJOv1562uY.jpg",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/eg2CakFFQh3dvujVj2qYCe7ybvK.jpg",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/r6SjKZSsVbdwVZhdutu4qvRe63c.jpg",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wHa6KOJAoNTFLFtp7wguUJKSnju.jpg",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/reKs8y4mPwPkZG99ZpbKRhBPKsX.jpg",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fi8EvaWtL5CvoielOjjVvTr7ux3.jpg",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rioMBKotMSu2lRIpGAaGRiDauAe.jpg",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/vqBsgL9nd2v04ZvCqPzwtckDdFD.jpg")

    private val tvshowsAired = arrayOf("February 15, 2007",
            "December 16, 1989",
            "February 26, 1986",
            "October 12, 2009",
            "October 7, 2014",
            "October 31, 2010",
            "September 23, 2003",
            "October 10, 2012",
            "March 17, 2017",
            "October 26, 2015")

    private val tvshowsGenre = arrayOf("Animation, Action & Adventure, Sci-Fi & Fantasy",
            "Animation, Comedy, Family, Drama",
            "Comedy, Sci-Fi & Fantasy, Animation, Action & Adventure",
            "Action & Adventure, Animation, Comedy, Sci-Fi & Fantasy",
            "Drama, Sci-Fi & Fantasy",
            "Action & Adventure, Drama, Sci-Fi & Fantasy",
            "Crime, Action & Adventure, Drama",
            "Crime, Drama, Mystery, Action & Adventure",
            "Action & Adventure, Drama, Sci-Fi & Fantasy",
            "Action, Adventure, Drama, Science Fiction")

    private val tvshowsStatus = arrayOf("Ended",
            "Returning Series",
            "Ended",
            "Ended",
            "Returning Series",
            "Returning Series",
            "Returning Series",
            "Ended",
            "Canceled",
            "Returning Series")

    private val tvshowsRating = doubleArrayOf(8.6,
            7.8,
            8.0,
            7.6,
            7.6,
            8.0,
            7.2,
            6.5,
            6.5,
            7.1)

    private val tvshowsLang = arrayOf("Japanese",
            "English",
            "Japanese",
            "Japanese",
            "English",
            "English",
            "English",
            "English",
            "English",
            "English")

    private val tvshowNet = arrayOf("TV Tokyo",
            "FOX",
            "Fuji TV",
            "TV Tokyo",
            "The CW",
            "AMC",
            "CBS",
            "The CW",
            "Netflix",
            "The CW & CBS")

    private val tvshowType = arrayOf("Scripted",
            "Scripted",
            "Scripted",
            "Scripted",
            "Scripted",
            "Scripted",
            "Scripted",
            "Scripted",
            "Scripted",
            "Scripted")

    private val tvshowsOverview = arrayOf("Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
            "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star.",
            "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed.",
            "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.",
            "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel.",
            "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
            "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
            "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
            "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
            "On the run from their pimp and his henchmen, three women embark on a wild and crazy journey in search of freedom.")

    private val tvshowsId = intArrayOf(111,
            112,
            113,
            114,
            115,
            116,
            117,
            118,
            119,
            120)

    fun generateDummyTv() : DetailTvEntity {
        lateinit var tvshow: DetailTvEntity
        for (position in tvshowsName.indices) {
            tvshow = DetailTvEntity()
            tvshow.name = tvshowsName[position]
            tvshow.release = tvshowsAired[position]
            tvshow.status = tvshowsStatus[position]
            tvshow.score = tvshowsRating[position]
            tvshow.type = tvshowType[position]
            tvshow.overview = tvshowsOverview[position]
            tvshow.tvId = tvshowsId[position]
            tvshow.image = tvshowsPoster[position]
        }
        return tvshow
    }

    fun generateRemoteDummyTv() : DetailTv {
        lateinit var tvshow: DetailTv
        for (position in tvshowsName.indices) {
            tvshow = DetailTv()
            tvshow.name = tvshowsName[position]
            tvshow.firstAirDate = tvshowsAired[position]
            tvshow.status = tvshowsStatus[position]
            tvshow.voteAverage = tvshowsRating[position]
            tvshow.type = tvshowType[position]
            tvshow.overview = tvshowsOverview[position]
            tvshow.id = tvshowsId[position]
            tvshow.posterPath = tvshowsPoster[position]
        }
        return tvshow
    }

    fun generateDummyGenreTv() : List<GenreTvEntity> {
        val genreResult = ArrayList<GenreTvEntity>()
        for (position in tvshowsName.indices) {
            val tvshow = GenreTvEntity()
            tvshow.name = tvshowsGenre[position]
            genreResult.add(tvshow)
        }
        return genreResult
    }

    fun generateRemoteDummyGenreTv() : List<GenreItem> {
        val genreResult = ArrayList<GenreItem>()
        for (position in tvshowsName.indices) {
            val tvshow = GenreItem()
            tvshow.name = tvshowsGenre[position]
            genreResult.add(tvshow)
        }
        return genreResult
    }

    fun generateDummyNetTv() : List<NetworkEntity> {
        val netResult = ArrayList<NetworkEntity>()
        for (position in tvshowsName.indices) {
            val tvshow = NetworkEntity()
            tvshow.name = tvshowNet[position]
            netResult.add(tvshow)
        }
        return netResult
    }

    fun generateRemoteDummyNetTv() : List<NetworksItem> {
        val netResult = ArrayList<NetworksItem>()
        for (position in tvshowsName.indices) {
            val tvshow = NetworksItem()
            tvshow.name = tvshowNet[position]
            netResult.add(tvshow)
        }
        return netResult
    }

    fun generateDummyLangTv() : List<LanguageTvEntity> {
        val langResult = ArrayList<LanguageTvEntity>()
        for (position in tvshowsName.indices) {
            val tvshow = LanguageTvEntity()
            tvshow.name = tvshowsLang[position]
            langResult.add(tvshow)
        }
        return langResult
    }

    fun generateRemoteDummyLangTv() : List<LanguagesItem> {
        val langResult = ArrayList<LanguagesItem>()
        for (position in tvshowsName.indices) {
            val tvshow = LanguagesItem()
            tvshow.name = tvshowsLang[position]
            langResult.add(tvshow)
        }
        return langResult
    }
}