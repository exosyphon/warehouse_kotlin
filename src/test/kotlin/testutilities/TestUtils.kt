package testutilities

import com.google.gson.JsonParser
import org.apache.commons.io.IOUtils
import java.io.IOException

object TestUtils {
    private val parser = JsonParser()

    @Throws(IOException::class)
    fun readJsonFixture(fixtureName: String, values: Map<String, Any> = emptyMap()): String {
        var jsonAsString: String? = IOUtils.toString(TestUtils::class.java.getResourceAsStream("/fixtures/$fixtureName"))

        for ((key, value) in values) {
            jsonAsString = jsonAsString!!.replace("==$key==".toRegex(), value.toString())
        }

        if (null != jsonAsString && jsonAsString.length > 0) {
            jsonAsString = jsonAsString.trim { it <= ' ' }
            val jsonElement = parser.parse(jsonAsString)
            return if (jsonAsString.startsWith("[")) {
                jsonElement.asJsonArray.toString()
            } else {
                jsonElement.asJsonObject.toString()
            }
        }

        throw IOException("Cannot find fixture: $fixtureName")
    }
}
