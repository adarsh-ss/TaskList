import Component from '@glimmer/component';
import {action} from "@ember/object";
import { inject as service } from '@ember/service';
import {tracked} from "@glimmer/tracking";

export default class ProfileComponent extends Component {
    constructor() {
        super(...arguments);
        this.model("");
      }

    @tracked response="";
    async model()
    {
        let response = await fetch("/TaskList/ListTask",
        {
            method: "POST",
            body: JSON.stringify({
                "userName": Cookies.get('user'),
            })
        });
        //console.log(user)
        this.response = await response.json();
        //console.log(parsed);
        //console.log(parsed.data[0].task_name);
        //return {parsed};
    }
    
    @action 
    async update(task){
        //this.status="Completed";
        let response=await fetch("/TaskList/TaskManagement",
        {   method: "POST",
            body: JSON.stringify({
                "operation": "update",
                "task": task
            })
        });
        let parsed =await response.json();
        alert(parsed.status);
        window.location.reload(true);
    }

    @action
    async delete (task){
        let response=await fetch("/TaskList/TaskManagement",
        {   method: "POST",
            body: JSON.stringify({
                "operation": "delete",
                "task": task
            })
        });
        let parsed=await response.json();
        alert(parsed.status);
        window.location.reload(true);
    }

}