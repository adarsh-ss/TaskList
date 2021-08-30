import Route from '@ember/routing/route';
import {inject as service} from '@ember/service';

export default class ProfileRoute extends Route {
    @service sess;

    beforeModel(transition) {
        //console.log(this.get('sess').isAuthenticated());
        if(this.get('sess').isAuthenticated() == false)
        {
            this.replaceWith('login');
        }
    }
}