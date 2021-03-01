/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androiddevchallenge.model.Puppy
import kotlin.random.Random

class PuppyViewModel(private val app: Application) : AndroidViewModel(app) {

    private val _puppyList = MutableLiveData(listOf<Puppy>())
    val puppyList: LiveData<List<Puppy>> = _puppyList

    private val _puppy: MutableLiveData<Puppy> = MutableLiveData()
    val puppy: LiveData<Puppy> = _puppy

    fun refresh() {
        _puppyList.value = (0..50).map { randomPuppy(app) }
    }

    fun setSelected(puppy: Puppy) {
        _puppy.value = puppy
    }
}

val breedList = listOf(
    R.string.welsh_corgi to R.string.welsh_corgi_description to R.drawable.dog1,
    R.string.siberian_husky to R.string.siberian_husky_description to R.drawable.dog2,
    R.string.dachshund to R.string.dachshund_description to R.drawable.dog3,
    R.string.beagle to R.string.beagle_description to R.drawable.dog,
    R.string.rottweiller to R.string.rottweiller_description to R.drawable.dog4,
    R.string.pug to R.string.pug_description to R.drawable.dog5,
    R.string.akita_inu to R.string.akita_inu_description to R.drawable.dog6,
    R.string.jack_russel_terrier to R.string.jack_russel_terrier_description to R.drawable.dog7,
    R.string.pomeranian to R.string.pomeranian_description to R.drawable.dog8,
    R.string.dalmatian to R.string.dalmatian_description to R.drawable.dog9,
    R.string.chihuahua to R.string.chihuahua_description to R.drawable.dog10,
    R.string.riesenschnauzer to R.string.riesenschnauzer_description to R.drawable.dog15,
    R.string.shih_tzu to R.string.shih_tzu_description to R.drawable.dog14,
    R.string.saint_bernard to R.string.saint_bernard_description to R.drawable.dog13,
    R.string.doberman_pincher to R.string.doberman_pincher_description to R.drawable.dog12,
)

fun randomPuppy(context: Context): Puppy {
    val femaleNames = context.resources.getStringArray(R.array.names_female)
    val maleNames = context.resources.getStringArray(R.array.names_male)
    val gender = Random.nextBoolean()
    val breed = breedList[Random.nextInt(breedList.size)]
    return Puppy(
        gender = gender,
        name = if (gender) femaleNames[Random.nextInt(femaleNames.size)] else maleNames[
            Random.nextInt(
                maleNames.size
            )
        ],
        breed = breed.first.first,
        image = breed.second,
        age = Random.nextInt(6, 24),
        description = context.getString(breed.first.second)
    )
}
