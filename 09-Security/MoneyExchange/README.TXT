****************************************************************************
* Security Sample - Money Exchange
* Das Beispiel zeigt den Aufruf von Business Methoden einer Stateless 
* Session Bean mit aktivierter Security. 
*
* Copyright (c) 2007, iten-engineering.ch
****************************************************************************

Damit das Beispiel ausgeführt werden kann sind auf dem Server folgende
Konfigurationen auszufüzen.

  >>>                                                           <<<
  >>> Für den Kurs sind die Einstellungen bereits konfiguriert! <<< 
  >>>                                                           <<<
  		
  a) Die Datei ..\jboss-<Version>\server\<Profil>\conf\login-config.xml 
     wir mit folgendem Eintrag ergänzt:

  		<!-- 
   		 * Security Sample - Money Exchange 
   		 -->
  		<application-policy name = "MoneyExchangeDomain">
  			<authentication>
         		<login-module code="org.jboss.security.auth.spi.UsersRolesLoginModule" flag="required">
           			<module-option name="usersProperties">props/mex-users.properties</module-option>
           			<module-option name="rolesProperties">props/mex-roles.properties</module-option>
           			<module-option name="unauthenticatedIdentity">guest</module-option>
          		</login-module>
       		</authentication>
  		</application-policy>


  b) Im Verzeichnis ..\jboss-<Version>\server\<Profil>\conf\propes werden 
     folgende Dateien mit den angegebenen Inhalt erstellt:
     
     mex-users.properties:
     	tom=secret
		sam=anothersecret
		admin=verysecret
     
     mex-roles.properties;
     	tom=Customer
		sam=Customer,VIP
		admin=Administrator
     
  c) Nach der Konfiguration ist der Application Server neu zu starten.
      
****************************************************************************


