import React, { Component } from 'react'
import GovHydroRService from '../services/GovHydroRService';

class UpdateGovHydroRComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
        }
        this.updateGovHydroR = this.updateGovHydroR.bind(this);

    }

    componentDidMount(){
        GovHydroRService.getGovHydroRById(this.state.id).then( (res) =>{
            let govHydroR = res.data;
            this.setState({
            });
        });
    }

    updateGovHydroR = (e) => {
        e.preventDefault();
        let govHydroR = {
            govHydroRId: this.state.id,
        };
        console.log('govHydroR => ' + JSON.stringify(govHydroR));
        console.log('id => ' + JSON.stringify(this.state.id));
        GovHydroRService.updateGovHydroR(govHydroR).then( res => {
            this.props.history.push('/govHydroRs');
        });
    }


    cancel(){
        this.props.history.push('/govHydroRs');
    }

    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Update GovHydroR</h3>
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                        </div>
                                        <button className="btn btn-success" onClick={this.updateGovHydroR}>Save</button>
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

export default UpdateGovHydroRComponent
