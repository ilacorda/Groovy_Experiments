import org.apache.nifi.processor.io.StreamCallback
import java.nio.charset.StandardCharsets
import org.apache.commons.io.IOUtils
import java.nio.charset.*


def flowFile = session.get()
if (!flowFile) return

def slurper = new groovy.json.JsonSlurper()

flowFile = session.write(flowFile, { inputStream, outputStream ->
    def text = IOUtils.toString(inputStream, StandardCharsets.UTF_8)
    def resultingText = text.substring(0, text.indexOf('}'))
    def json = slurper.parseText(resultingText)

    outputStream.write(json.toString().getBytes(StandardCharsets.UTF_8))

} as StreamCallback)

session.transfer(flowFile, REL_SUCCESS)



