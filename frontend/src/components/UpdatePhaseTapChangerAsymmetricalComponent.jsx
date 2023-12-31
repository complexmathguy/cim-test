import React, { Component } from 'react'
import PhaseTapChangerAsymmetricalService from '../services/PhaseTapChangerAsymmetricalService';

class UpdatePhaseTapChangerAsymmetricalComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
        }
        this.updatePhaseTapChangerAsymmetrical = this.updatePhaseTapChangerAsymmetrical.bind(this);

    }

    componentDidMount(){
        PhaseTapChangerAsymmetricalService.getPhaseTapChangerAsymmetricalById(this.state.id).then( (res) =>{
            let phaseTapChangerAsymmetrical = res.data;
            this.setState({
            });
        });
    }

    updatePhaseTapChangerAsymmetrical = (e) => {
        e.preventDefault();
        let phaseTapChangerAsymmetrical = {
            phaseTapChangerAsymmetricalId: this.state.id,
        };
        console.log('phaseTapChangerAsymmetrical => ' + JSON.stringify(phaseTapChangerAsymmetrical));
        console.log('id => ' + JSON.stringify(this.state.id));
        PhaseTapChangerAsymmetricalService.updatePhaseTapChangerAsymmetrical(phaseTapChangerAsymmetrical).then( res => {
            this.props.history.push('/phaseTapChangerAsymmetricals');
        });
    }


    cancel(){
        this.props.history.push('/phaseTapChangerAsymmetricals');
    }

    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Update PhaseTapChangerAsymmetrical</h3>
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                        </div>
                                        <button className="btn btn-success" onClick={this.updatePhaseTapChangerAsymmetrical}>Save</button>
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

export default UpdatePhaseTapChangerAsymmetricalComponent
