<?xml version="1.0"?>
<recipe>

<#if isRxFluxFragment>
	<instantiate from="root/src/app_package/RxFluxFragment.java.ftl"
                  to="${escapeXmlAttribute(srcOut)}/${fragmentClass}.java" />
	<open file="${escapeXmlAttribute(srcOut)}/${fragmentClass}.java" />
<#else>
	<instantiate from="root/src/app_package/Fragment.java.ftl"
               	  to="${escapeXmlAttribute(srcOut)}/${fragmentClass}.java" />
	<open file="${escapeXmlAttribute(srcOut)}/${fragmentClass}.java" />
</#if>

<#if generateLayout>
    <instantiate from="root/res/layout/fragment.xml.ftl"
                  to="${escapeXmlAttribute(resOut)}/layout/${layoutName}.xml" />
    <open file="${escapeXmlAttribute(resOut)}/layout/${layoutName}.xml" />
</#if>

</recipe>
