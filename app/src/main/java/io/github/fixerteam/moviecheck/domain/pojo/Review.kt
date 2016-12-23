/*
 * Copyright 2015.  Emin Yahyayev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.fixerteam.moviecheck.domain.pojo

import android.os.Parcel
import android.os.Parcelable

class Review() : Parcelable {
  var id: String? = null
  var author: String? = null
  var content: String? = null
  var url: String? = null

  constructor(source: Parcel) : this() {
    this.id = source.readString()
    this.author = source.readString()
    this.content = source.readString()
    this.url = source.readString()
  }

  override fun describeContents(): Int = 0

  override fun writeToParcel(dest: Parcel, flags: Int) {
    dest.writeString(this.id)
    dest.writeString(this.author)
    dest.writeString(this.content)
    dest.writeString(this.url)
  }

  class Response {
    var id: Long = 0
    var page: Int = 0
    var results: List<Review> = emptyList()
    var total_pages: Int = 0
    var total_results: Int = 0
  }

  companion object {
    val CREATOR: Parcelable.Creator<Review> = object : Parcelable.Creator<Review> {
      override fun createFromParcel(source: Parcel): Review = Review(source)
      override fun newArray(size: Int): Array<Review?> = arrayOfNulls(size)
    }
  }
}