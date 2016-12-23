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
import com.fasterxml.jackson.annotation.JsonProperty

class Video() : Parcelable {

  @JsonProperty("iso_639_1")
  var iso: String? = null
  var id: String? = null
  var key: String? = null
  var name: String? = null
  var site: String? = null
  var size: Int = 0
  var type: String? = null

  override fun describeContents() = 0

  override fun writeToParcel(dest: Parcel, flags: Int) {
    dest.writeString(this.iso)
    dest.writeString(this.id)
    dest.writeString(this.key)
    dest.writeString(this.name)
    dest.writeString(this.site)
    dest.writeInt(this.size)
    dest.writeString(this.type)
  }

  constructor(source: Parcel) : this() {
    this.iso = source.readString()
    this.id = source.readString()
    this.key = source.readString()
    this.name = source.readString()
    this.site = source.readString()
    this.size = source.readInt()
    this.type = source.readString()
  }

  class Response {
    var results: List<Video> = emptyList()
    var id: Long = 0
  }

  companion object {
    val SITE_YOUTUBE = "YouTube"
    val TYPE_TRAILER = "Trailer"

    val CREATOR: Parcelable.Creator<Video> = object : Parcelable.Creator<Video> {
      override fun createFromParcel(source: Parcel) = Video(source)
      override fun newArray(size: Int): Array<Video?> = arrayOfNulls(size)
    }
  }
}
