package school.enrollment



import org.junit.*
import grails.test.mixin.*

@TestFor(AppRoleController)
@Mock(AppRole)
class AppRoleControllerTests {


    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/appRole/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.appRoleInstanceList.size() == 0
        assert model.appRoleInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.appRoleInstance != null
    }

    void testSave() {
        controller.save()

        assert model.appRoleInstance != null
        assert view == '/appRole/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/appRole/show/1'
        assert controller.flash.message != null
        assert AppRole.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/appRole/list'


        populateValidParams(params)
        def appRole = new AppRole(params)

        assert appRole.save() != null

        params.id = appRole.id

        def model = controller.show()

        assert model.appRoleInstance == appRole
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/appRole/list'


        populateValidParams(params)
        def appRole = new AppRole(params)

        assert appRole.save() != null

        params.id = appRole.id

        def model = controller.edit()

        assert model.appRoleInstance == appRole
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/appRole/list'

        response.reset()


        populateValidParams(params)
        def appRole = new AppRole(params)

        assert appRole.save() != null

        // test invalid parameters in update
        params.id = appRole.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/appRole/edit"
        assert model.appRoleInstance != null

        appRole.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/appRole/show/$appRole.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        appRole.clearErrors()

        populateValidParams(params)
        params.id = appRole.id
        params.version = -1
        controller.update()

        assert view == "/appRole/edit"
        assert model.appRoleInstance != null
        assert model.appRoleInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/appRole/list'

        response.reset()

        populateValidParams(params)
        def appRole = new AppRole(params)

        assert appRole.save() != null
        assert AppRole.count() == 1

        params.id = appRole.id

        controller.delete()

        assert AppRole.count() == 0
        assert AppRole.get(appRole.id) == null
        assert response.redirectedUrl == '/appRole/list'
    }
}
