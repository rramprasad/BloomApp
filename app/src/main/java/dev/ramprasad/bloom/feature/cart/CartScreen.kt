/*
 * Created by Ramprasad Ranganathan on 02/06/21, 2:07 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 01/06/21, 2:45 PM
 */

package dev.ramprasad.bloom.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.ramprasad.bloom.R

@Composable
fun CartScreen() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colors.background)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.cart),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier
                    .paddingFromBaseline(32.dp, 0.dp)
                    .padding(16.dp, 16.dp)
            )
        }
    }
}