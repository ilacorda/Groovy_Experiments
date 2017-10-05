import org.apache.commons.io.IOUtils
import org.apache.nifi.processor.io.StreamCallback

import java.nio.charset.StandardCharsets

def flowFile = session.get()
if (!flowFile) return

flowFile = session.write(flowFile, { inputStream, outputStream ->
    def text = IOUtils.toString(inputStream, StandardCharsets.UTF_8)
    def regex = /\s",/
    def textTransformation = text[0..text.lastIndexOf('}')].replace('""', '"').replace('test.text."', 'test.text.')
    def finalTransformation = textTransformation.replace(regex, '"",')
    outputStream.write(finalTransformation.toString().getBytes(StandardCharsets.UTF_8))

} as StreamCallback)

session.transfer(flowFile, REL_SUCCESS)