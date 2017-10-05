import org.apache.nifi.processor.io.StreamCallback
import java.nio.charset.StandardCharsets

def flowFile = session.get()
if (!flowFile) return

flowFile = session.write(flowFile, { inputStream, outputStream ->
    inputStream.eachLine { line, number ->
        if (number == 1)
            return // continue
        outputStream.write(line.toString().getBytes(StandardCharsets.UTF_8))
    }
} as StreamCallback)

session.transfer(flowFile, REL_SUCCESS)