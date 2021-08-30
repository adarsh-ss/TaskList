import Controller from '@ember/controller';
import {action} from "@ember/object";
import { tracked } from "@glimmer/tracking";
import { inject as service } from '@ember/service';

export default class loginController extends Controller {
    @service sess;
    //@tracked errorMessage;    


    @action
    async login()
    {        
        let{userName, password} = this.getProperties('userName', 'password');
        let response=await fetch("/TaskList/Login",
        {   method: "POST",
            body: JSON.stringify({
                "userName": userName,
                "password": password
            })
        });
        let parsed=await response.json();
        
        if(parsed.status == "Authentication successful")
        {
            this.get('sess').login(userName)
            //console.log(userName)
            this.transitionToRoute('profile')
        }
        else
        {
            alert(parsed.status)
            window.location.reload(true);
        }
    }
        
}

 //};

 /*
    @action
    async authenticate(e) 
    {
        e.preventDefault();
        let { username, password } = this;
        try 
        {
            await this.session.authenticate('authenticator:oauth2', username, password);
        } 
        catch(error) 
        {
            this.errorMessage = error.error || error;
        }

        if (this.session.isAuthenticated) 
        {
            $.cookie("user", username);
            this.transitionToRoute('profile');
            // What to do with all this success?
        }
    }

    @action
    updateUsername(e) 
    {
        this.username = e.target.value;
    }

    @action
    updatePassword(e) 
    {
        this.password = e.target.value;
    }

    @action
    async login()
    {
        
        
        
        let{userName, password} = this.getProperties('userName', 'password');
        let response=await fetch("/TaskList/Login",
        {   method: "POST",
            body: JSON.stringify({
                "userName": userName,
                "password": password
            })
        });
        let parsed=await response.json();
        
        if(parsed.status == "Authentication successful")
        {
            this.get('session').login(userName)
            //console.log(userName)
            this.transitionToRoute('profile')
        }
        else
        {
            alert(parsed.status)
            window.location.reload(true);
        }
        */