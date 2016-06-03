package com.experiments.groovy.scripts

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

class LoadConfigFileAndReplaceValues {

    static void main(String[] args) {

        def content = '''
        {
        "field1":"someText",
        "field2":"anotherText"
        }
        '''

        def slurper = new JsonSlurper().parseText(content)
        def builder = new JsonBuilder(slurper)

        assert builder.content.field1 == "someText"
        assert builder.content.field2 == "anotherText"

        def propertiesFile = new File('D:\\Dev\\Groovy_Experiments\\src\\main\\resources\\properties.txt')
        Properties props = new Properties()
        props.load(new FileInputStream(propertiesFile))
        def conf = new ConfigSlurper().parse(props).flatten();
        println "This prints the content of the properties file: " + conf

        conf.each { k, v ->
            if (builder.content[k]) {
                builder.content[k] = v
            }
            println("This prints the resulting JSON :" + builder.toPrettyString())
        }
    }
}

