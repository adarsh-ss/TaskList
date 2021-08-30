import Controller from '@ember/controller';
import {action} from "@ember/object";
import { inject as service } from '@ember/service';

var user;

export default class profileController extends Controller {
    @service sess;

    user = Cookies.get('user');
    @action
    logout()
    {
        this.get("sess").logout();
        this.transitionToRoute('login');
        window.location.reload(true);
    }
 };