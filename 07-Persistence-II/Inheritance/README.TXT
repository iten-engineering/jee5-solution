----------------------------------------------------------------------------
Inheritance Samples 
Copyright (c) 2007, iten-engineering.ch
----------------------------------------------------------------------------

Setup:
	no setup required


Samples:
	I1 - single table per class hierarchy
	I2 - single table per concrete entity class
	I3 - single table per subclass / joined strategy

Entity names:
	All entities have a unique name within the inheritance project.
	The name is defined by the @Entity annotation. 
	
	Samples:
	
	Entity Name		Entity Class 
  	PersonI1		ch.aspect1.inheritance.i1.domain.Person
  	PersonI2		ch.aspect1.inheritance.i2.domain.Person


Table names:
	Also all tables have a unique name, starting with the relationship
	sample number (i.e. I1_PERSON, I3_PERSON, etc).

----------------------------------------------------------------------------
