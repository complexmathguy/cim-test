import React, { Component } from 'react'
import DiscExcContIEEEDEC2AService from '../services/DiscExcContIEEEDEC2AService';

class UpdateDiscExcContIEEEDEC2AComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
        }
        this.updateDiscExcContIEEEDEC2A = this.updateDiscExcContIEEEDEC2A.bind(this);

    }

    componentDidMount(){
        DiscExcContIEEEDEC2AService.getDiscExcContIEEEDEC2AById(this.state.id).then( (res) =>{
            let discExcContIEEEDEC2A = res.data;
            this.setState({
            });
        });
    }

    updateDiscExcContIEEEDEC2A = (e) => {
        e.preventDefault();
        let discExcContIEEEDEC2A = {
            discExcContIEEEDEC2AId: this.state.id,
        };
        console.log('discExcContIEEEDEC2A => ' + JSON.stringify(discExcContIEEEDEC2A));
        console.log('id => ' + JSON.stringify(this.state.id));
        DiscExcContIEEEDEC2AService.updateDiscExcContIEEEDEC2A(discExcContIEEEDEC2A).then( res => {
            this.props.history.push('/discExcContIEEEDEC2As');
        });
    }


    cancel(){
        this.props.history.push('/discExcContIEEEDEC2As');
    }

    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Update DiscExcContIEEEDEC2A</h3>
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                        </div>
                                        <button className="btn btn-success" onClick={this.updateDiscExcContIEEEDEC2A}>Save</button>
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

export default UpdateDiscExcContIEEEDEC2AComponent
