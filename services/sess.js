import Cookies from 'ember-cli-js-cookie';
import Service, { inject as service } from '@ember/service';

let currentUser = null;

export default class SessService extends Service {
    @service store;

    constructor()
    {
        super(...arguments);
        console.log(this.store);
    }

    login(userName)
    {
        //console.log(userName);
        currentUser = userName;
        Cookies.set('user', userName);
    }
    logout()
    {
        currentUser = null;
        Cookies.remove('user');
        
    }
    
    isAuthenticated()
    {
        if(!!currentUser)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    init(){
        super.init(...arguments);
        var users =  Cookies.get('user');
        if(!!users)
        {
            currentUser = users;
        }
    }
}
