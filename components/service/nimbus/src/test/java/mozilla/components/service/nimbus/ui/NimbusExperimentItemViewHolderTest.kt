/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package mozilla.components.service.nimbus.ui

import android.view.View
import android.widget.TextView
import androidx.test.ext.junit.runners.AndroidJUnit4
import mozilla.components.support.test.mock
import mozilla.components.support.test.robolectric.testContext
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import org.mozilla.experiments.nimbus.EnrolledExperiment
import java.util.UUID

@RunWith(AndroidJUnit4::class)
class NimbusExperimentItemViewHolderTest {

    private val experiment = EnrolledExperiment(
        slug = "secure-gold",
        branchSlug = "control",
        userFacingDescription = "This is a test experiment for diagnostic purposes.",
        userFacingName = "Diagnostic test experiment",
        enrollmentId = UUID.randomUUID().toString(),
        featureIds = listOf("secure-gold")
    )
    private lateinit var nimbusExperimentsDelegate: NimbusExperimentsAdapterDelegate
    private lateinit var titleView: TextView
    private lateinit var summaryView: TextView

    @Before
    fun setup() {
        nimbusExperimentsDelegate = mock()
        titleView = mock()
        summaryView = mock()
    }

    @Test
    fun `sets text on view`() {
        val view = View(testContext)
        val holder =
            NimbusExperimentItemViewHolder(view, nimbusExperimentsDelegate, titleView, summaryView)

        holder.bind(experiment)
        verify(titleView).text = experiment.userFacingName
        verify(summaryView).text = experiment.userFacingDescription
    }

    @Test
    fun `sets click listener`() {
        val view = View(testContext)
        val holder =
            NimbusExperimentItemViewHolder(view, nimbusExperimentsDelegate, titleView, summaryView)

        holder.bind(experiment)
        holder.itemView.performClick()
        verify(nimbusExperimentsDelegate).onExperimentItemClicked(experiment)
    }
}
