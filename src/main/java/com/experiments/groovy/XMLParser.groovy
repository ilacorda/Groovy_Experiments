package com.experiments.groovy

import groovy.util.XmlParser;
import groovy.util.XmlSlurper;


def xmlStructure = '''
    <state>
        <county>
            <city>London</city>
        </county>
    </state>
'''

def hierarchyTags = new XmlSlurper().parseText(xmlStructure)