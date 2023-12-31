import React, { Component } from 'react'
import DCGroundService from '../services/DCGroundService';

class CreateDCGroundComponent extends Component {
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
            DCGroundService.getDCGroundById(this.state.id).then( (res) =>{
                let dCGround = res.data;
                this.setState({
                });
            });
        }        
    }
    saveOrUpdateDCGround = (e) => {
        e.preventDefault();
        let dCGround = {
                dCGroundId: this.state.id,
            };
        console.log('dCGround => ' + JSON.stringify(dCGround));

        // step 5
        if(this.state.id === '_add'){
            dCGround.dCGroundId=''
            DCGroundService.createDCGround(dCGround).then(res =>{
                this.props.history.push('/dCGrounds');
            });
        }else{
            DCGroundService.updateDCGround(dCGround).then( res => {
                this.props.history.push('/dCGrounds');
            });
        }
    }
    

    cancel(){
        this.props.history.push('/dCGrounds');
    }

    getTitle(){
        if(this.state.id === '_add'){
            return <h3 className="text-center">Add DCGround</h3>
        }else{
            return <h3 className="text-center">Update DCGround</h3>
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

                                        <button className="btn btn-success" onClick={this.saveOrUpdateDCGround}>Save</button>
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

export default CreateDCGroundComponent
