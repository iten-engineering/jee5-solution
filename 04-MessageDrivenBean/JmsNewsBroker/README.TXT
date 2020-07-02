****************************************************************************
* JMS Sample - News Broker
* Das Beispiel zeigt die Verwendung von JMS für eine Point to Point und 
* Publish - Subscribe Kommunikation. 
*
* Copyright (c) 2007, iten-engineering.ch
****************************************************************************

Damit das Beispiel ausgeführt werden kann sind folgende Punkte zu beachten:

   I Auf dem Server ist eine Queue mit dem Namen "testQueue" einzurichten. 
    
  II Auf dem Server ist eine Topic mit dem Namen "testTopic" einzurichten. 
  
  		>>>                                                <<<
  		>>> Beide Einstellungen sind bereits konfiguriert! <<< 
  		>>>                                                <<<
  		
  --------------------------------------------------------------------------	
  Server Konfiguration
  --------------------------------------------------------------------------	

  a) Eintrag Queue Definiton in der Datei destinations-service.xml im       
     Verzeichnis ...\jboss-5\server\default\deploy\messaging:
 
   	 <!--
       Define a testTopic
   	 -->
     <mbean code="org.jboss.jms.server.destination.TopicService"
        name="jboss.messaging.destination:service=Topic,name=testTopic"
        xmbean-dd="xmdesc/Topic-xmbean.xml">
        
        <depends optional-attribute-name="ServerPeer">
          jboss.messaging:service=ServerPeer
        </depends>
        
        <depends>
          jboss.messaging:service=PostOffice
        </depends>
        
        <attribute name="SecurityConfig">
          <security>
            <role name="guest" read="true" write="true"/>
            <role name="publisher" read="true" write="true" create="false"/>
            <role name="durpublisher" read="true" write="true" create="true"/>
          </security>
        </attribute>
     </mbean>
   
  b) Eintrag Topic Definiton in der Datei destinations-service.xml im       
     Verzeichnis ...\jboss-5\server\default\deploy\messaging:
   
     <!--
       Define a testQueue
     -->
     <mbean code="org.jboss.jms.server.destination.QueueService"
       name="jboss.messaging.destination:service=Queue,name=testQueue"
       xmbean-dd="xmdesc/Queue-xmbean.xml">
       
       <depends optional-attribute-name="ServerPeer">
         jboss.messaging:service=ServerPeer
       </depends>
       
       <depends>
         jboss.messaging:service=PostOffice
       </depends>
       
       <attribute name="SecurityConfig">
         <security>
           <role name="guest" read="true" write="true"/>
           <role name="publisher" read="true" write="true" create="false"/>
           <role name="noacc" read="false" write="false" create="false"/>    
         </security>
       </attribute>
   </mbean>
 
   c) JBoss starten und im JMX Menu jboss.messaging.destination prüfen
      ob die neuen Einträge sichtbar sind.

****************************************************************************


