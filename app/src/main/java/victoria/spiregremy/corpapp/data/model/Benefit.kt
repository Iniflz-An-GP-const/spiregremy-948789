package victoria.spiregremy.corpapp.data.model

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AssignmentTurnedIn
import androidx.compose.material.icons.outlined.Insights
import androidx.compose.material.icons.outlined.SupportAgent

/**
 * [texts] maps a two-letter language code ("en", "de", ...) to the benefit text.
 * The "en" entry is mandatory: it is the fallback.
 */
data class Benefit(
    val icon: ImageVector,
    val texts: Map<String, String>,
) {
    val text: String get() = texts.forCurrentLocale()
}

val defaultServiceBenefits: List<Benefit> = listOf(
    Benefit(Icons.Outlined.Insights, mapOf(
        "en" to "Evidence-based advice", "de" to "Fundierte Beratung",
        "es" to "Asesoría con evidencia", "fr" to "Conseils fondés",
        "hi" to "तथ्य-आधारित सलाह", "id" to "Saran berbasis bukti",
        "pt" to "Orientação com evidências", "ar" to "استشارة قائمة على الأدلة",
    )),
    Benefit(Icons.Outlined.AssignmentTurnedIn, mapOf(
        "en" to "Practical action plan", "de" to "Praktischer Aktionsplan",
        "es" to "Plan de acción práctico", "fr" to "Plan d\'action concret",
        "hi" to "व्यावहारिक कार्य योजना", "id" to "Rencana aksi praktis",
        "pt" to "Plano de ação prático", "ar" to "خطة عمل عملية",
    )),
    Benefit(Icons.Outlined.SupportAgent, mapOf(
        "en" to "Follow-up support", "de" to "Nachbetreuung",
        "es" to "Soporte posterior", "fr" to "Suivi dédié",
        "hi" to "फ़ॉलो-अप सहायता", "id" to "Dukungan lanjutan",
        "pt" to "Suporte contínuo", "ar" to "دعم للمتابعة",
    )),
)
