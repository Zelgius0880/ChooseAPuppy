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
package com.example.androiddevchallenge.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.androiddevchallenge.PuppyViewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.randomPuppy

@Composable
fun PuppyDetails(navController: NavController, viewModel: PuppyViewModel) {
    val nullablePuppy: Puppy? by viewModel.puppy.observeAsState(null)

    if (nullablePuppy != null) {
        val puppy = nullablePuppy!!
        Column(Modifier.verticalScroll(rememberScrollState())) {
            PuppyInformation(puppy = puppy, Modifier.padding(8.dp))
            PuppyDescription(puppy = puppy, Modifier.padding(8.dp))
        }
    }
}

@Composable
fun PuppyInformation(puppy: Puppy, modifier: Modifier = Modifier) {
    Card(modifier = Modifier.fillMaxWidth() then modifier) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = stringResource(id = R.string.about_format, puppy.name),
                style = MaterialTheme.typography.h6
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(puppy.image),
                    contentDescription = null,
                    modifier = Modifier.size(72.dp) then Modifier.background(
                        color = MaterialTheme.colors.secondaryVariant,
                        shape = RoundedCornerShape(24.dp)
                    ) then Modifier.padding(5.dp)
                )

                Column(modifier = Modifier.padding(start = 8.dp)) {
                    Text(text = puppy.name, style = MaterialTheme.typography.subtitle2)
                    Text(text = stringResource(id = puppy.breed))
                    Text(text = stringResource(id = R.string.age_format, puppy.age))
                    Text(text = stringResource(id = if (puppy.gender) R.string.female else R.string.male))
                }
            }
        }
    }
}

@Composable
fun PuppyDescription(puppy: Puppy, modifier: Modifier = Modifier) {
    Card(modifier = Modifier.fillMaxWidth() then modifier) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = stringResource(id = R.string.description, puppy.name),
                style = MaterialTheme.typography.h6
            )
            Text(text = puppy.description)
        }
    }
}

@Preview
@Composable
fun PuppyInformationPreview() {
    PuppyInformation(puppy = randomPuppy(LocalContext.current))
}

@Preview
@Composable
fun PuppyDescriptionPreview() {
    PuppyDescription(puppy = randomPuppy(LocalContext.current))
}
