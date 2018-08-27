// tag::packageDeclaration[]
package demo

import grails.gorm.transactions.ReadOnly
import groovy.transform.CompileStatic
import org.grails.datastore.mapping.multitenancy.web.SessionTenantResolver

// end::packageDeclaration[]
// tag::importStatements[]

// end::importStatements[]

// tag::classDeclaration[]
@CompileStatic
class ManufacturerController {
// end::classDeclaration[]

    // tag::index[]
    @ReadOnly
    def index() {
        render view: '/index', model: [manufacturers: Manufacturer.list()]
    }
    // end::index[]

    // tag::select[]
    @ReadOnly
    def select(String id) {
        Manufacturer m = Manufacturer.where {
            name == id
        }.first() // <1>
        if ( m ) {
            session.setAttribute(SessionTenantResolver.ATTRIBUTE, m.name.toLowerCase()) // <2>
            redirect controller: 'vehicle' // <3>
        }
        else {
            render status: 404
        }
    }
    // end::select[]

// tag::closeBracket[]
}
// end::closeBracket[]
