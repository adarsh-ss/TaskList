import Route from '@ember/routing/route';
import { inject as service } from '@ember/service';

export default class LoginRoute extends Route {
    @service sess;

    beforeModel(transition) 
    {
        //console.log("in login " + this.get('sess').isAuthenticated());
       if(this.get('sess').isAuthenticated() == true)
        {
            this.replaceWith('profile');
        }
    }
}
