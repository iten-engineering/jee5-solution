****************************************************************************
* JMS Sample - MDB
*
* Das Beispiel zeigt die Verwendung von JMS für eine Point to Point 
* Kommunikation zwischen einer Java (standalone) Applikationen und einem
* Message Driven Bean.
*
* Copyright (c) 2007, iten-engineering.ch
****************************************************************************

Damit das Beispiel ausgeführt werden kann, ist auf dem Server eine 
Queue mit dem Namen "p2pQueue" einzurichten. 

Dazu sind folgenden Schritte notwendig:

  a) Ergänzen Sie die Datei destinations-service.xml 
     im Verzeichnis ...\jboss-5\server\default\deploy\messaging
     mit folgendem Eintrag:
   
   <!--
     Define a p2pQueue
   -->
   <mbean code="org.jboss.jms.server.destination.QueueService"
     name="jboss.messaging.destination:service=Queue,name=p2pQueue"
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
 
   b) Im JBoss JMX Menu jboss.messaging.destination prüfen ob die 
      definierte Queue vorhanden ist.
      
      Hinweis: Es ist kein Restart vom JBoss notwendig.

****************************************************************************


