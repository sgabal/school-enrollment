package school.enrollment

import groovy.util.logging.Log4j
import org.codehaus.groovy.grails.commons.metaclass.DynamicMethodInvocation

/**
* A mixin allowing domain object to be converted to maps. Example:
*
* <code>
*         def account = new Account()
*         def map = bindProperties(account)
* </code>
*
* @author Sam Gabal
*/

@Log4j
class PersistentPropertiesMethod implements DynamicMethodInvocation {


	def persistentProperties(arguments) {
		def object = arguments.domain
		def excludes = arguments.excludes
		def includeTransients = arguments.includeTransients
		
		def response = [:]

		def domainClass = object.domainClass
		
		def persistentProperties = domainClass.persistentProperties.findAll { !excludes?.contains(it.name) }
		
		response."${domainClass.identifier.name}" = object."${domainClass.identifier.name}"
		
		persistentProperties.each  {
			if (!it.isAssociation()) {
				response."${it.name}" = object."${it.name}"
			} 
		}
		
		if (includeTransients) {
			domainClass.properties.findAll { !it.isPersistent() }.each {
				response."${it.name}" = object."${it.name}"
			}
		}
		
		return response
	}
	
	def allPersistentProperties(arguments) {
		def object = arguments.domain
		def excludes = arguments.excludes
		def includeSingleEnded = arguments.includeSingleEnded
		def includeTransients = arguments.includeTransients
		
		def response = [:]
		
		def domainClass = object.domainClass
		
		response."${domainClass.identifier.name}" = object."${domainClass.identifier.name}"
		
		def persistentProperties = domainClass.persistentProperties.findAll { !excludes?.contains(it.name) }
		
		persistentProperties.each  {
			if (!it.isAssociation()) {
				response."${it.name}" = object."${it.name}"
			} else if (it.isOneToMany()) {
				response."${it.name}" = object."${it.name}".collect {
					bindProperties(domain:it, excludes:excludes)
				}
			} else if (includeSingleEnded && (it.isOneToOne() || it.isManyToOne()) && object."${it.name}" != null) {
				response."${it.name}" = bindProperties(domain:object."${it.name}", excludes:excludes)
			}
		}
		
		if (includeTransients) {
			domainClass.properties.findAll { !it.isPersistent() }.each {
				response."${it.name}" = object."${it.name}"
			}
		}
		
		return response
	}
	
	def logProperties(object) {
		def domainClass = object.domainClass
		
		if (log.isDebugEnabled()) {
			domainClass.persistentProperties.each {
				log.debug domainClass.name + ".${it.name}=" + object."${it.name}"
			}
		}
	}

	boolean isMethodMatch(String methodName) {
		return methodName == 'persistentProperties' || methodName == 'allPersistentProperties' || methodName == 'logProperties';
	}

	Object invoke(Object target, String methodName, Object[] arguments) {
		return this."$methodName"(*arguments)
	}


	
}
