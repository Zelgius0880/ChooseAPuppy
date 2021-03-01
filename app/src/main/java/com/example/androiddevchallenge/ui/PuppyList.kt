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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.Destination
import com.example.androiddevchallenge.PuppyViewModel
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.randomPuppy
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun PuppyListItem(item: Puppy, onPuppyClicked: (Puppy) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth() then Modifier.clickable(true) {
            onPuppyClicked(item)
        }
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(item.image),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp) then Modifier.background(
                        color = MaterialTheme.colors.secondaryVariant,
                        shape = RoundedCornerShape(24.dp)
                    ) then Modifier.padding(5.dp)
                )

                Column(modifier = Modifier.padding(start = 8.dp)) {
                    Text(text = item.name, style = MaterialTheme.typography.subtitle2)
                    Text(text = stringResource(id = item.breed))
                }
            }
        }
    }
}

@Preview
@Composable
fun PuppyListItemPreview() {
    PuppyListItem(item = randomPuppy(LocalContext.current)) {}
}

@Preview
@Composable
fun PuppyListItemDarkPreview() {
    MyTheme(darkTheme = true) {
        PuppyListItem(item = randomPuppy(LocalContext.current)) {}
    }
}

@Composable
fun PuppyList(navController: NavController, viewModel: PuppyViewModel) {
    val puppies: List<Puppy> by viewModel.puppyList.observeAsState(listOf())

    LazyColumn(
        contentPadding = PaddingValues(8.dp, 0.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(puppies) { puppy ->
            PuppyListItem(item = puppy) {
                viewModel.setSelected(puppy)
                navController.navigate(Destination.PUPPY_DETAILS.name)
            }
        }
    }
}
