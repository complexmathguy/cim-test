import React, { Component } from 'react'
import PowerSystemStabilizerUserDefinedService from '../services/PowerSystemStabilizerUserDefinedService';

class UpdatePowerSystemStabilizerUserDefinedComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
        }
        this.updatePowerSystemStabilizerUserDefined = this.updatePowerSystemStabilizerUserDefined.bind(this);

    }

    componentDidMount(){
        PowerSystemStabilizerUserDefinedService.getPowerSystemStabilizerUserDefinedById(this.state.id).then( (res) =>{
            let powerSystemStabilizerUserDefined = res.data;
            this.setState({
            });
        });
    }

    updatePowerSystemStabilizerUserDefined = (e) => {
        e.preventDefault();
        let powerSystemStabilizerUserDefined = {
            powerSystemStabilizerUserDefinedId: this.state.id,
        };
        console.log('powerSystemStabilizerUserDefined => ' + JSON.stringify(powerSystemStabilizerUserDefined));
        console.log('id => ' + JSON.stringify(this.state.id));
        PowerSystemStabilizerUserDefinedService.updatePowerSystemStabilizerUserDefined(powerSystemStabilizerUserDefined).then( res => {
            this.props.history.push('/powerSystemStabilizerUserDefineds');
        });
    }


    cancel(){
        this.props.history.push('/powerSystemStabilizerUserDefineds');
    }

    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Update PowerSystemStabilizerUserDefined</h3>
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                        </div>
                                        <button className="btn btn-success" onClick={this.updatePowerSystemStabilizerUserDefined}>Save</button>
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

export default UpdatePowerSystemStabilizerUserDefinedComponent
