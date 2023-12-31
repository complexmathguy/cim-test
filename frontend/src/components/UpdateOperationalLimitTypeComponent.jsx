import React, { Component } from 'react'
import OperationalLimitTypeService from '../services/OperationalLimitTypeService';

class UpdateOperationalLimitTypeComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
                direction: '',
                limitType: ''
        }
        this.updateOperationalLimitType = this.updateOperationalLimitType.bind(this);

        this.changeDirectionHandler = this.changeDirectionHandler.bind(this);
        this.changeLimitTypeHandler = this.changeLimitTypeHandler.bind(this);
    }

    componentDidMount(){
        OperationalLimitTypeService.getOperationalLimitTypeById(this.state.id).then( (res) =>{
            let operationalLimitType = res.data;
            this.setState({
                direction: operationalLimitType.direction,
                limitType: operationalLimitType.limitType
            });
        });
    }

    updateOperationalLimitType = (e) => {
        e.preventDefault();
        let operationalLimitType = {
            operationalLimitTypeId: this.state.id,
            direction: this.state.direction,
            limitType: this.state.limitType
        };
        console.log('operationalLimitType => ' + JSON.stringify(operationalLimitType));
        console.log('id => ' + JSON.stringify(this.state.id));
        OperationalLimitTypeService.updateOperationalLimitType(operationalLimitType).then( res => {
            this.props.history.push('/operationalLimitTypes');
        });
    }

    changeDirectionHandler= (event) => {
        this.setState({direction: event.target.value});
    }
    changeLimitTypeHandler= (event) => {
        this.setState({limitType: event.target.value});
    }

    cancel(){
        this.props.history.push('/operationalLimitTypes');
    }

    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Update OperationalLimitType</h3>
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                            <label> Direction: </label>
                                            #formFields( $attribute, 'update')
                                            <label> LimitType: </label>
                                            #formFields( $attribute, 'update')
                                        </div>
                                        <button className="btn btn-success" onClick={this.updateOperationalLimitType}>Save</button>
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

export default UpdateOperationalLimitTypeComponent
