import React, { Component } from 'react'
import DCShuntService from '../services/DCShuntService'

class ViewDCShuntComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            dCShunt: {}
        }
    }

    componentDidMount(){
        DCShuntService.getDCShuntById(this.state.id).then( res => {
            this.setState({dCShunt: res.data});
        })
    }

    render() {
        return (
            <div>
                <br></br>
                <div className = "card col-md-6 offset-md-3">
                    <h3 className = "text-center"> View DCShunt Details</h3>
                    <div className = "card-body">
                    </div>
                </div>
            </div>
        )
    }
}

export default ViewDCShuntComponent
