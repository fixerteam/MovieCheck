package io.github.fixerteam.moviecheck.data.datasource.local.local_model

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class GenreId(var id: Int = 0) : RealmObject()