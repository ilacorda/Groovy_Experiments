import org.apache.commons.io.IOUtils
import org.apache.nifi.processor.io.StreamCallback

import java.nio.charset.StandardCharsets

def flowFile = session.get()
if (!flowFile) return

flowFile = session.write(flowFile, { inputStream, outputStream ->
    def text = IOUtils.toString(inputStream, StandardCharsets.UTF_8)
    def resultingText = text[0..text.lastIndexOf('}')].replaceAll('""', '"')

    outputStream.write(resultingText.toString().getBytes(StandardCharsets.UTF_8))

} as StreamCallback)

session.transfer(flowFile, REL_SUCCESS)