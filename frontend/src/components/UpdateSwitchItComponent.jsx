import React, { Component } from 'react'
import SwitchItService from '../services/SwitchItService';

class UpdateSwitchItComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
        }
        this.updateSwitchIt = this.updateSwitchIt.bind(this);

    }

    componentDidMount(){
        SwitchItService.getSwitchItById(this.state.id).then( (res) =>{
            let switchIt = res.data;
            this.setState({
            });
        });
    }

    updateSwitchIt = (e) => {
        e.preventDefault();
        let switchIt = {
            switchItId: this.state.id,
        };
        console.log('switchIt => ' + JSON.stringify(switchIt));
        console.log('id => ' + JSON.stringify(this.state.id));
        SwitchItService.updateSwitchIt(switchIt).then( res => {
            this.props.history.push('/switchIts');
        });
    }


    cancel(){
        this.props.history.push('/switchIts');
    }

    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Update SwitchIt</h3>
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                        </div>
                                        <button className="btn btn-success" onClick={this.updateSwitchIt}>Save</button>
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

export default UpdateSwitchItComponent
