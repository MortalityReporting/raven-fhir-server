# Changelog

## [4.0.4](https://github.com/nightingaleproject/VRDR_javalib/compare/4.0.3...4.0.4) (2023-08-17)

### Features

* create a parser for nchs bundle of bundles creating the java objects with correct type in a list ([#57](https://github.com/nightingaleproject/VRDR_javalib/issues/57)) ([d4bb552](https://github.com/nightingaleproject/VRDR_javalib/commit/d4bb552f0a0e82ebab1db4b5af55fc2c4fb4e1a7))


### Bug Fixes

* correct YesNo code system url ([9124807](https://github.com/nightingaleproject/VRDR_javalib/commit/9124807237be054222cb3f050917a3c764be8638))
* ensure proper handling of unknown times in fhir structure for da… ([#68](https://github.com/nightingaleproject/VRDR_javalib/issues/68)) ([4eb19fb](https://github.com/nightingaleproject/VRDR_javalib/commit/4eb19fb7870a5cdc78dee6dddb4a3bb8fa39a656))
* missing Unknown and Other in the PlaceOfDeath ValueSet definition ([#62](https://github.com/nightingaleproject/VRDR_javalib/issues/62)) ([90a00fd](https://github.com/nightingaleproject/VRDR_javalib/commit/90a00fd230d556881b4f329d634d426648450cfe))
* remove extra space ([012bb43](https://github.com/nightingaleproject/VRDR_javalib/commit/012bb436b4fe39e2281520889ee8479862b8ec5b))
* update block_count to be an unsigned int for DeathRecordVoidMessage ([#64](https://github.com/nightingaleproject/VRDR_javalib/issues/64)) ([f8ae237](https://github.com/nightingaleproject/VRDR_javalib/commit/f8ae23732bb328cf08f22690f15abb3f0497d2c4))

## 4.0.3 - 2023-06-07

* Update and sync examples files (json and xml) with IG as follows:
    * Revert removal of transportation role as a component of injury incident (but keeping removal as a no longer valid profile)
    * Update cause of death coding and demographic coding message eventUri to match IG

## 4.0.2 - 2023-06-05

* Update and sync examples files (json and xml) with IG as follows:
    * Remove Transport Role and its references
    * Remove Death Certificate Reference and its references
    * Remove Death Pronouncement Performer and its references
    * Remove Decedent Employment History and its references
    * Remove Cause of Death Pathway and its references
    * Change Mortician, Funeral Home Director, Funeral Service Licensee to us-core-practitioner
    * Change Interested Party to us-core-organization
    * Change Decendent Pregnancy to decedent-pregnancy-status
    * Change Cause of Death Condition to cause-of-death-part1
    * Change Cause of Death Condition Contributing Death to cause-of-death-part2

## 4.0.1 - 2023-05-16

* Sync of race and ethnicity literal fields with VRDR IG latest build
* Added support for valueTime in Date of Death Pronounced as per VRDR IG
* Removed Mortician class as not useful or used in the library
* Corrected numerous FHIR profile ID and mapping errors

## v1.4.3-STU2.1 - 2023-04-26

* Race Literal Helper Methods

## v1.4.2-STU2.1 - 2023-04-25

* Race Literal AlaskanNative typo fix

## v1.4.1-STU2.1 - 2023-04-20

* Numerous bug fixes found as part of testing certification with UT
* Race Literal handling update as per VRDR IG

## v1.4.0-STU2 - 2023-02-10

* Added support for VRDR Messaging IG
* New package "messaging" for VRDR Messaging IG models
* New package "messaging.util" for messaging helper classes separate from the IG models
* VRDRFhirContext now contains all data structure and messaging definitions and is the primary context use case
* New class VRDRFhirContextDataStructuresOnly is a context class with only the data structure definitions
* New package edu.gatech.chai.VRDR.messaging contains all messaging structure definitions in the IG
* Besides IG classes, messaging also contains BaseMessage and UnknownMessage classes for assisting in message handling
* New class MessagingTest contains thorough messaging tests for VRDR Messaging IG flows
* Test resources now contains FHIR json and xml files for use by the MessagingTest class
* Generic parser method on BaseMessage for parsing bundles and messages
* Added xml and json test files and tests
* Added CauseOfDeath and Demographics coded bundle handling
* Tests complete for all major flows
* Added CLI for generating canary tests
* Added CHANGELOG for tracking changes with each release
* Updated README

