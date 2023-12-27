import React, { Component } from 'react'
import RegularIntervalScheduleService from '../services/RegularIntervalScheduleService';

class CreateRegularIntervalScheduleComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            // step 2
            id: this.props.match.params.id,
        }
    }

    // step 3
    componentDidMount(){

        // step 4
        if(this.state.id === '_add'){
            return
        }else{
            RegularIntervalScheduleService.getRegularIntervalScheduleById(this.state.id).then( (res) =>{
                let regularIntervalSchedule = res.data;
                this.setState({
                });
            });
        }        
    }
    saveOrUpdateRegularIntervalSchedule = (e) => {
        e.preventDefault();
        let regularIntervalSchedule = {
                regularIntervalScheduleId: this.state.id,
            };
        console.log('regularIntervalSchedule => ' + JSON.stringify(regularIntervalSchedule));

        // step 5
        if(this.state.id === '_add'){
            regularIntervalSchedule.regularIntervalScheduleId=''
            RegularIntervalScheduleService.createRegularIntervalSchedule(regularIntervalSchedule).then(res =>{
                this.props.history.push('/regularIntervalSchedules');
            });
        }else{
            RegularIntervalScheduleService.updateRegularIntervalSchedule(regularIntervalSchedule).then( res => {
                this.props.history.push('/regularIntervalSchedules');
            });
        }
    }
    

    cancel(){
        this.props.history.push('/regularIntervalSchedules');
    }

    getTitle(){
        if(this.state.id === '_add'){
            return <h3 className="text-center">Add RegularIntervalSchedule</h3>
        }else{
            return <h3 className="text-center">Update RegularIntervalSchedule</h3>
        }
    }
    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                {
                                    this.getTitle()
                                }
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                        </div>

                                        <button className="btn btn-success" onClick={this.saveOrUpdateRegularIntervalSchedule}>Save</button>
                                        <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                   </div>
            </div>
        )
    }
}

export default CreateRegularIntervalScheduleComponent
