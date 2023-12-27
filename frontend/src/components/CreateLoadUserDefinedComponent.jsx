import React, { Component } from 'react'
import LoadUserDefinedService from '../services/LoadUserDefinedService';

class CreateLoadUserDefinedComponent extends Component {
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
            LoadUserDefinedService.getLoadUserDefinedById(this.state.id).then( (res) =>{
                let loadUserDefined = res.data;
                this.setState({
                });
            });
        }        
    }
    saveOrUpdateLoadUserDefined = (e) => {
        e.preventDefault();
        let loadUserDefined = {
                loadUserDefinedId: this.state.id,
            };
        console.log('loadUserDefined => ' + JSON.stringify(loadUserDefined));

        // step 5
        if(this.state.id === '_add'){
            loadUserDefined.loadUserDefinedId=''
            LoadUserDefinedService.createLoadUserDefined(loadUserDefined).then(res =>{
                this.props.history.push('/loadUserDefineds');
            });
        }else{
            LoadUserDefinedService.updateLoadUserDefined(loadUserDefined).then( res => {
                this.props.history.push('/loadUserDefineds');
            });
        }
    }
    

    cancel(){
        this.props.history.push('/loadUserDefineds');
    }

    getTitle(){
        if(this.state.id === '_add'){
            return <h3 className="text-center">Add LoadUserDefined</h3>
        }else{
            return <h3 className="text-center">Update LoadUserDefined</h3>
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

                                        <button className="btn btn-success" onClick={this.saveOrUpdateLoadUserDefined}>Save</button>
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

export default CreateLoadUserDefinedComponent
